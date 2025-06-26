package com.riu.practice.palabrota.domain.service;

import com.riu.practice.palabrota.domain.model.LetterModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WordAlgorithm {
    public List<LetterModel> compareLetters(String[] objectiveLetters, String[] attemptLetters) {
        String[] originalAttemptLetters = attemptLetters.clone();
        boolean[] matchedObjective = new boolean[objectiveLetters.length];
        List<LetterModel> result = markCorrectPositions(objectiveLetters, attemptLetters, originalAttemptLetters, matchedObjective);
        markMisplacedLetters(objectiveLetters, attemptLetters, originalAttemptLetters, matchedObjective, result);

        return result;
    }

    private List<LetterModel> markCorrectPositions(String[] objectiveLetters, String[] attemptLetters, String[] originalAttemptLetters, boolean[] matchedObjective) {
        List<LetterModel> result = new ArrayList<>();
        for (int i = 0; i < attemptLetters.length; i++) {
            if (i < objectiveLetters.length && attemptLetters[i].equals(objectiveLetters[i])) {
                matchedObjective[i] = true;
                result.add(new LetterModel(originalAttemptLetters[i], 2));
            } else {
                result.add(null);
            }
        }

        return result;
    }

    private void markMisplacedLetters(String[] objectiveLetters, String[] attemptLetters, String[] originalAttemptLetters, boolean[] matchedObjective, List<LetterModel> result) {
        for (int i = 0; i < attemptLetters.length; i++) {
            if (result.get(i) != null) continue;
            int foundIndex = findMisplacedIndex(objectiveLetters, attemptLetters[i], matchedObjective);
            if (foundIndex != -1) {
                matchedObjective[foundIndex] = true;
                result.set(i, new LetterModel(originalAttemptLetters[i], 1));
            } else {
                result.set(i, new LetterModel(originalAttemptLetters[i], 0));
            }
        }
    }

    private int findMisplacedIndex(String[] objectiveLetters, String attemptLetter, boolean[] matchedObjective) {
        for (int j = 0; j < objectiveLetters.length; j++) {
            if (!matchedObjective[j] && attemptLetter.equals(objectiveLetters[j])) {
                return j;
            }
        }

        return -1;
    }
}
