package com.dreamsearcher.sender.error;

public class EmailSendTroubleException extends RuntimeException
{
    public EmailSendTroubleException()
    {

    }

    public EmailSendTroubleException(String message)
    {
        super(message);
    }
}
