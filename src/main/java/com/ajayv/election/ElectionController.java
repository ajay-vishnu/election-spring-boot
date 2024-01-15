package com.ajayv.election;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
public class ElectionController {

  @Autowired
  ElectionService electionService;

  @PostMapping(path = "entercandidate")
  public void enterCandidate(@RequestParam String name)  {
    electionService.enterCandidate(name);
  }

  @PutMapping(path = "castvote")
  public void castvote(@RequestParam String name)  {
    electionService.castvote(name);
  }

  @GetMapping(path = "countvote")
  public Integer countvote(@RequestParam String name)  {
    return electionService.countvote(name);
  }

  @GetMapping(path = "listvote")
  public Map<Long, ElectionObject> listvote()  {
    return electionService.listvote();
  }

  @GetMapping(path = "getwinner")
  public String getwinner()  {
    return electionService.getwinner();
  }
}
