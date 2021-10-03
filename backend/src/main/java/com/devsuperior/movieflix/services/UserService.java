package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dtos.RoleDTO;
import com.devsuperior.movieflix.dtos.UserDTO;
import com.devsuperior.movieflix.dtos.UserInsertDTO;
import com.devsuperior.movieflix.dtos.UserUpdateDTO;
import com.devsuperior.movieflix.entities.Role;
import com.devsuperior.movieflix.services.exceptions.DataBaseException;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import com.devsuperior.movieflix.services.exceptions.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.RoleRepository;
import com.devsuperior.movieflix.repositories.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class UserService implements UserDetailsService {
	
	private static Logger LOG  = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder ;
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Transactional(readOnly = true)
	public Page<UserDTO> findAllPaged(PageRequest pageRequest) {
		return repository.findAll(pageRequest).map(UserDTO::new);
	}

	@Transactional(readOnly = true)
	public UserDTO findById(Long id) {
		User user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
		return new UserDTO(user);
	}

	@Transactional
	public UserDTO save(UserInsertDTO dto) {
		User user = new User();
		copyDtoToEntity(dto, user);
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		return new UserDTO(repository.save(user));
	}

	@Transactional
	public UserDTO update(Long id, UserUpdateDTO dto) {
		try {
			User user = repository.getOne(id);
			copyDtoToEntity(dto, user);
			return new UserDTO(repository.save(user));
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id não encontrado: "+id);
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Integridade violada: "+id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id não encontrado: "+id);
		}
	}

	private void copyDtoToEntity(UserDTO dto, User user) {
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.getRoles().clear();
		for (RoleDTO roleDTO : dto.getRoles()) {
			Role role = roleRepository.getOne(roleDTO.getId());
			user.getRoles().add(role);
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByEmail(username);
		if (user == null) {
			LOG.error("Email not found:"+username);
			throw new UsernameNotFoundException("Email not found"); 
		}
		LOG.info("User found:"+username);
		return user;
	}

	@Transactional(readOnly = true)
	public User authenticated() {
		try {
			final var userName = SecurityContextHolder.getContext().getAuthentication().getName();
			return repository.findByEmail(userName);
		} catch (Exception e) {
			throw new UnauthorizedException("Invalid user");
		}
	}

}
