package com.epam.ekc.storage.controller;

import static java.util.UUID.randomUUID;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.epam.ekc.storage.model.Agent;
import com.epam.ekc.storage.model.Instance;
import com.epam.ekc.storage.model.Work;
import com.epam.ekc.storage.repository.AgentRepository;
import com.epam.ekc.storage.repository.InstanceRepository;
import com.epam.ekc.storage.repository.WorkRepository;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
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
@RequestMapping("api/works")
public class WorkController {

  private final WorkRepository workRepository;
  private final InstanceRepository instanceRepository;
  private final AgentRepository agentRepository;

  @PostMapping(consumes = APPLICATION_JSON_VALUE)
  public Work save(@RequestBody @Validated Work work) {
    work.setId(randomUUID().toString());
    savePassedAgents(work.getAgents());
    savePassedInstances(work.getInstances());
    return workRepository.save(work);
  }

  @PostMapping(value = "/batch", consumes = APPLICATION_JSON_VALUE)
  public List<Work> saveAll(@RequestBody List<Work> works) {
    works.forEach(a -> a.setId(randomUUID().toString()));
    return workRepository.saveAll(works);
  }

  @GetMapping
  public Page<Work> findAll(@RequestParam int page, @RequestParam int size) {
    log.debug("Request to get all Works");
    return workRepository.findAll(PageRequest.of(page, size));
  }

  @GetMapping("/{id}")
  public Optional<Work> findOne(@PathVariable String id) {
    log.debug("Request to get Work : {}", id);
    return workRepository.findById(id);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable String id) {
    log.debug("Request to delete Work : {}", id);
    workRepository.deleteById(id);
  }

  private void savePassedInstances(Collection<Instance> instances) {
    instances.forEach(instance -> instance.setId(randomUUID().toString()));
    instanceRepository.saveAll(instances);
  }

  private void savePassedAgents(Collection<Agent> agents) {
    agents.stream()
        .filter(agent -> agent.getId() == null)
        .forEach(agent -> agent.setId(randomUUID().toString()));
    agentRepository.saveAll(agents);
  }
}
