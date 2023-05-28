package com.example.services.validation;


import com.example.enums.Currency;
import com.example.repository.CardRepository;
import com.example.enums.CardType;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.regex.Pattern;

import static com.example.enums.Currency.*;


public class Validation {

    private CardRepository cardRepository;

    ExchangeRate exchangeRate = new ExchangeRate();

    Random random = new Random();

    Crypt crypt = new Crypt();

    public String cardValidation(String type) {
        StringBuilder stringBuilder = new StringBuilder();
        String cartType = "";
        int length = 0;
        switch (CardType.valueOf(type)) {
            case VISA:
                cartType = "4";
                length = 15;
                break;
            case MASTER_CARD:
                cartType = String.valueOf(random.nextInt(51, 56));
                length = 14;
                break;
            case AMERICAN_EXPRESS:
                cartType = String.valueOf(random.nextInt(34, 38));
                length = 13;
        }
        stringBuilder.append(cartType);
        for (int i = 0; i < length; i++) {
            stringBuilder.append(random.nextInt(9));
        }
        return stringBuilder.toString();
    }


    public String validateDateForTransaction() {
        Format f = new SimpleDateFormat("MM/yy");
        return f.format(new Date());
    }

    public String validateDate() {
        Format f = new SimpleDateFormat("MM/yy");
        StringBuilder sb = new StringBuilder();
        sb.append(f.format(new Date()));
        int date = Integer.parseInt(sb.substring(sb.length() - 2)) + 5;
        return sb.replace(sb.length() - 2, sb.length(), Integer.toString(date)).toString();
    }

    public String cvcValidation() throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            stringBuilder.append(random.nextInt(9));
        }
        String cvc = Arrays.toString(Crypt.encrypt(stringBuilder.toString()));
        return cvc;
    }

    public String pinValidation() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            stringBuilder.append(random.nextInt(9));
        }
        return stringBuilder.toString();
    }


    public boolean isNumeric(String pin) {
        String regex = "[0-9]{4}";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(pin).matches();

    }

    public String generateTransactionNumber() {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());

        Random random = new Random();
        int randomNumber = random.nextInt((int) Math.pow(10, 4));

        String formattedRandomNumber = String.format("%0" + 4 + "d", randomNumber);

        return timestamp + formattedRandomNumber;
    }

    public String accountValidation(String currency) {
        StringBuilder stringBuilder = new StringBuilder();
        String firstFourDigits = "2060";
        String lastFourDigits = "";
        int length = 8;
        switch (Currency.valueOf(currency)) {
            case AMD:
                lastFourDigits = "1111";
                break;
            case USD:
                lastFourDigits = "2222";
                break;
            case EUR:
                lastFourDigits = "3333";
                break;
            case RUR:
                lastFourDigits = "4444";
                break;
        }
        stringBuilder.append(firstFourDigits);
        for (int i = 0; i < length; i++) {
            stringBuilder.append(random.nextInt(9));
        }
        stringBuilder.append(lastFourDigits);
        return stringBuilder.toString();
    }

    public double getRate(String currencyTypeOne, String currencyTypeTwo, double amount) {
        double sendAmount = 1;
        double receiveAmount = 1;
        switch (Currency.valueOf(currencyTypeOne)) {
            case RUR:
                sendAmount = amount * exchangeRate.getRUR();
                break;
            case USD:
                sendAmount = amount * exchangeRate.getUSD();
                break;
            case AMD:
                sendAmount = amount;
                break;
            case EUR:
                sendAmount = amount * exchangeRate.getEUR();

        }
        switch (Currency.valueOf(currencyTypeTwo)) {
            case RUR:
                receiveAmount = sendAmount / exchangeRate.getRUR();
                break;
            case USD:
                receiveAmount = sendAmount / exchangeRate.getUSD();
                break;
            case AMD:
                receiveAmount = sendAmount;
                break;
            case EUR:
                receiveAmount = sendAmount / exchangeRate.getEUR();
        }
        return receiveAmount;
    }
}
