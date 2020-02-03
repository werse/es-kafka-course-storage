package com.epam.ekc.storage.controller;

import static java.util.UUID.randomUUID;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.epam.ekc.storage.model.Instance;
import com.epam.ekc.storage.repository.InstanceRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/instances")
public class InstanceController {

  private final InstanceRepository instanceRepository;

  @PostMapping(consumes = APPLICATION_JSON_VALUE)
  public Instance save(@RequestBody Instance instance) {
    instance.setId(randomUUID().toString());
    return instanceRepository.save(instance);
  }

  @PostMapping(value = "/batch", consumes = APPLICATION_JSON_VALUE)
  public List<Instance> saveAll(@RequestBody List<Instance> instances) {
    instances.forEach(a -> a.setId(randomUUID().toString()));
    return instanceRepository.saveAll(instances);
  }

  @GetMapping
  public Page<Instance> findAll(@RequestParam int page, @RequestParam int size) {
    log.debug("Request to get all Instances");
    return instanceRepository.findAll(PageRequest.of(page, size));
  }

  @GetMapping("/{id}")
  public Optional<Instance> findOne(@PathVariable String id) {
    log.debug("Request to get Instance : {}", id);
    return instanceRepository.findById(id);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable String id) {
    log.debug("Request to delete Instance : {}", id);
    instanceRepository.deleteById(id);
  }
}
