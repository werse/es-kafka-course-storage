package com.epam.ekc.storage.controller;

import static java.util.UUID.randomUUID;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.epam.ekc.storage.model.Agent;
import com.epam.ekc.storage.repository.AgentRepository;
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
@RequestMapping("/api/agents")
public class AgentController {

  private final AgentRepository agentRepository;

  @PostMapping(consumes = APPLICATION_JSON_VALUE)
  public Agent save(@RequestBody Agent agent) {
    agent.setId(randomUUID().toString());
    return agentRepository.save(agent);
  }

  @PostMapping(value = "/batch", consumes = APPLICATION_JSON_VALUE)
  public List<Agent> saveAll(@RequestBody List<Agent> agent) {
    agent.forEach(a -> a.setId(randomUUID().toString()));
    return agentRepository.saveAll(agent);
  }

  @GetMapping
  public Page<Agent> findAll(@RequestParam int page, @RequestParam int size) {
    log.debug("Request to get all Agents");
    return agentRepository.findAll(PageRequest.of(page, size));
  }

  @GetMapping("/{id}")
  public Optional<Agent> findOne(@PathVariable String id) {
    log.debug("Request to get Agent : {}", id);
    return agentRepository.findById(id);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable String id) {
    log.debug("Request to delete Agent : {}", id);
    agentRepository.deleteById(id);
  }
}
