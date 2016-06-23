package org.cilab.s4rm.controller;

import java.beans.PropertyDescriptor;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.cilab.s4rm.model.Stream;
import org.cilab.s4rm.model.Stream_Meta;
import org.cilab.s4rm.service.StreamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api(value = "streams")
@RestController
public class StreamController {

	/**
	 * Class Name: StreamController.java 
	 * Description: CRUD, Service
	 * 
	 * @author Meilan Jiang
	 * @since 2016.06.16
	 * @version 1.2
	 * 
	 *          Copyright(c) 2016 by CILAB All right reserved.
	 */
	private static final Logger logger = LoggerFactory.getLogger(StreamController.class);

	@Autowired
	private StreamService streamService;
	

	// -------------------- Read and Search Stream Collection Resource
	// --------------------
	@RequestMapping(value = "/streams", method = RequestMethod.GET)
	public ResponseEntity<List<Stream>> list(@RequestParam(required = false) MultiValueMap<String, String> params) {
		// read Stream collection resource when there is no parameter.
		if (params.isEmpty()) {
			logger.info("Reading Stream Collection Resource ...");
			List<Stream> streams = streamService.readCollection();
			if (streams.isEmpty()) {
				logger.info("No Streams found.");
				return new ResponseEntity<List<Stream>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Stream>>(streams, HttpStatus.OK);
		}
		// search Stream collection resource with parameters.
		else {
			logger.info("Searching Stream Resource ...");

			PropertyDescriptor[] props = BeanUtils.getPropertyDescriptors(Stream.class);
			List<String> variables = new ArrayList<String>();
			for (PropertyDescriptor desc : props) {
				variables.add(desc.getName());
			}
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			for (String key : params.keySet()) {
				if (variables.contains(key)) {
					// uppercase first letter of property name
					String param = key.substring(0, 1).toUpperCase();
					param = param + key.substring(1);

					List<String> values = new ArrayList<String>();
					for(String value: params.get(key)){
						// decode parameters
						try {
							values.add(new String(value.getBytes("8859_1"), "UTF-8"));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					}
					map.put(param, values);
				} else
					logger.info("Unexpected Parameter :{} has been removed.", key);
			}
			if (map.keySet().size() == 0) {
				return new ResponseEntity<List<Stream>>(HttpStatus.BAD_REQUEST);
			} else {
				List<Stream> streams = this.streamService.listSearch(map);
				if (streams.isEmpty() || streams == null)
					return new ResponseEntity<List<Stream>>(HttpStatus.NOT_FOUND);
				else
					return new ResponseEntity<List<Stream>>(streams, HttpStatus.OK);
			}
		}
	}

	// -------------------- Create a Stream Instance Resource ------------------
	@RequestMapping(value = "/streams/new", method = RequestMethod.POST)
	public ResponseEntity<Boolean> create(@RequestBody Stream stream) {
		logger.info("Creating Stream Instance Resource of Name: {} ..." + stream.getName());
		// check if stream contains the Not Null field in the database.
		if (stream.getName() == null)
			return new ResponseEntity<Boolean>(HttpStatus.BAD_REQUEST);

		if (streamService.isInstanceExist(stream.getCreatedAt(), stream.getSensorID())) {
			logger.info("A Stream with name {} already exist.", stream.getName());
			return new ResponseEntity<Boolean>(HttpStatus.CONFLICT);
		}else{

			boolean createRes = streamService.newInstance(stream);
			
			return new ResponseEntity<Boolean>(createRes, HttpStatus.CREATED);
		}
	}
	
	// -------------------- Read a Stream Instance Resource --------------------
	@RequestMapping(value = "/streams/{id}", method = RequestMethod.GET)
	public ResponseEntity<Stream> read(@PathVariable("id") String streamID) {
		logger.info("Reading Stream Instance Resource of ID: {} ...", streamID);
		Stream stream = this.streamService.readInstance(streamID);
		if (stream == null) {
			logger.info("Stream Instance Resource of ID: {}, not found.", streamID);
			return new ResponseEntity<Stream>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Stream>(stream, HttpStatus.OK);
	}

	// -------------------- Update a Stream Instance Resource ------------------
	@RequestMapping(value = "/streams/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Boolean> update(@RequestBody Stream stream, @PathVariable("id") String streamID) {
		logger.info("Updating Stream Instance Resource of ID: {} ...", stream.getId());

		if (streamID.equals(stream.getId())) {
			Set<Stream_Meta> metaSet = new HashSet<Stream_Meta>();	
			for(Stream_Meta meta: stream.getMetas()){
				meta.setStreamID(stream.getId());
				metaSet.add(meta);
			}
			stream.setMetas(metaSet);
			Boolean res = this.streamService.updateInstance(stream);
			
			if (res)
				return new ResponseEntity<Boolean>(res, HttpStatus.OK);
			else
				return new ResponseEntity<Boolean>(res, HttpStatus.CONFLICT);
		}else {
			logger.info("Stream Instance Resource of ID: {} , {} doesn't match.", streamID, stream.getId());
			return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
		} 
		
	}

	// -------------------- Delete a Stream Instance Resource ------------------
	@RequestMapping(value = "/streams/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable("id") String streamID) {
		logger.info("Reading & Deleting Stream Instance Resource of ID: {} ...", streamID);
		Stream stream = this.streamService.readInstance(streamID);
		if (stream == null) {
			logger.info("Unable to delete. Stream Instance Resource of ID: {}, not found.", streamID);
			return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
		}
		Boolean res = this.streamService.deleteInstance(streamID);
		if (res)
			return new ResponseEntity<Boolean>(res, HttpStatus.OK);
		else
			return new ResponseEntity<Boolean>(res, HttpStatus.CONFLICT); // when Stream has existing related Sites
	}

	// -------------------- Search for Stream Resource --------------------
	@RequestMapping(value = "/streams", method = RequestMethod.POST)
	public ResponseEntity<List<Stream>> search(@RequestBody Map<String, List<String>> reqMap) {
		logger.info("Searching Stream Resource ...");

		// remove the parameters which doesn't match with column in the list
		PropertyDescriptor[] params = BeanUtils.getPropertyDescriptors(Stream.class);
		List<String> variables = new ArrayList<String>();
		for (PropertyDescriptor desc : params) {
			variables.add(desc.getName());
		}
		
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		for (String key : reqMap.keySet()) {
			if(variables.contains(key)){
				String param = key.substring(0,1).toUpperCase();
				param = param + key.substring(1);
				map.put(param, reqMap.get(key));
			}
			else
				logger.info("Unexpected Parameter :{} has been removed.", key);
		}
		List<Stream> streams = this.streamService.listSearch(map);
		if (streams.isEmpty() || streams == null)
			return new ResponseEntity<List<Stream>>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<List<Stream>>(streams, HttpStatus.OK);
	}

}
