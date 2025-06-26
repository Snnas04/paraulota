package com.riu.practice.palabrota.domain.port.service;

import com.riu.practice.palabrota.domain.model.AttemptResultModel;
import com.riu.practice.palabrota.domain.model.DoAttemptModel;

public interface AttemptService {
    AttemptResultModel check(DoAttemptModel attempt);
}
