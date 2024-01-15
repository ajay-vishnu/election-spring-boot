package com.ajayv.election;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
public class ElectionController {

  @Autowired
  ElectionService electionService;
  @PostMapping(path = "{candidateName}")

  public void enterCandidate(@PathVariable("candidateName") String candidateName)  {
    electionService.enterCandidate(candidateName);
  }

  @PutMapping(path = "{candidateName}")
  public void castvote(@PathVariable("candidateName") String candidateName)  {
    electionService.castvote(candidateName);
  }

  @GetMapping(path = "{candidateName}")
  public Integer countvote(@PathVariable("candidateName") String candidateName)  {
    return electionService.countvote(candidateName);
  }

  @GetMapping
  public Map<Long, ElectionObject> listvote()  {
    return electionService.listvote();
  }

  @GetMapping(path = "getwinner")
  public String getwinner()  {
    return electionService.getwinner();
  }
}
