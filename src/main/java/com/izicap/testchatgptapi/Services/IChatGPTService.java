package com.izicap.testchatgptapi.Services;

import com.izicap.testchatgptapi.Models.Question;
import com.izicap.testchatgptapi.Models.Response;

public interface IChatGPTService {
    Response getChatGPTAnswer(Question question, String APIKey);
}
