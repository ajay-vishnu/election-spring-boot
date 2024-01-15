package com.ajayv.election;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ElectionService {

  private Map<Long, ElectionObject> inMemoryStorage = new HashMap<>();
  private Long idCounter = 1L;

  public ElectionObject save(ElectionObject electionObject) {
    electionObject.setId(idCounter++);
    inMemoryStorage.put(electionObject.getId(), electionObject);
    return electionObject;
  }

  public void enterCandidate(String candidateName) {
    ElectionObject electionObject = new ElectionObject();
    electionObject.setCandidateName(candidateName);
    electionObject.setVoteCount(0);
  }

  public void castvote(String candidateName) {
    Optional<ElectionObject> optionalObject = inMemoryStorage.values()
        .stream()
        .filter(electionObject -> candidateName.equals(electionObject.getCandidateName()))
        .findFirst();
    if (optionalObject.isPresent()) {
      ElectionObject electionObject = optionalObject.get();
      Integer voteCount = electionObject.getVoteCount();
      electionObject.setVoteCount(voteCount + 1);
    }
  }

  public Map<Long, ElectionObject> listvote() {
    return inMemoryStorage;
  }

  public Integer countvote(String candidateName) {
    Optional<ElectionObject> optionalObject = inMemoryStorage.values()
        .stream()
        .filter(electionObject -> candidateName.equals(electionObject.getCandidateName()))
        .findFirst();
    if (optionalObject.isPresent()) {
      ElectionObject electionObject = optionalObject.get();
      return electionObject.getVoteCount();
    } else {
      throw new IllegalStateException("Candidate doesn't exist");
    }
  }

  public String getwinner() {
    Optional<ElectionObject> optionalObject = inMemoryStorage.values()
        .stream()
        .max(Comparator.comparingInt(ElectionObject::getVoteCount));
    if (optionalObject.isPresent()) {
      ElectionObject electionObject = optionalObject.get();
      return electionObject.getCandidateName();
    } else {
      throw new IllegalStateException("No candidates found in the list");
    }
  }
}
