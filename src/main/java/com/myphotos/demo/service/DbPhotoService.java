package com.myphotos.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myphotos.demo.model.Photo;
import com.myphotos.demo.repository.IphotoRepository;

@Service("MainPhotoService")
public class DbPhotoService implements IphotoService{

	@Autowired
	private IphotoRepository iphotoRepository;
	
	@Override
	public Iterable<Photo> getAll(){
		
		return iphotoRepository.findAll();
		
	}
	
	@Override
	public Optional<Photo> getById(int id){
		
		return iphotoRepository.findById(id);	
		
	}
	
	@Override
	public Photo create(Photo photo) {
		
		return iphotoRepository.save(photo);
		
	}
	
	@Override
	public Optional<Photo> update(int id,Photo photo) {
		
		Optional<Photo> foundPhoto = iphotoRepository.findById(id);
		
		if(foundPhoto.isEmpty()) {
			
			return Optional.empty();
		}
		
		foundPhoto.get().setUrl(photo.getUrl());
		
		iphotoRepository.save(foundPhoto.get());
		
		return foundPhoto;
		
	}
	
	@Override
	public Boolean delete(int id) {
			
		Optional<Photo> foundPhoto = iphotoRepository.findById(id);
		
		if(foundPhoto.isEmpty()) {
			return false;
		}
		
		iphotoRepository.delete(foundPhoto.get());
		
		return true;
		
	}

}
