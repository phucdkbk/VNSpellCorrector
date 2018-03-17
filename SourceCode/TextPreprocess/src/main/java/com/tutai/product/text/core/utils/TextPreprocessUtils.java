/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutai.product.text.core.utils;

import com.tutai.product.text.core.database.TextResource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author quangvh
 */
public class TextPreprocessUtils {

    public static String toTrainSample(String input) {
        String output = input;
        output = transformToUTF8(output);
        output = autoCorrectVowel(output);
        output = output.replaceAll("\\s+", " ").trim();
        return output;
    }

    public static String toTrainSampleRegardSpace(String input) {
        String output = input;
        output = transformToUTF8(output);
        output = autoCorrectVowel(output);
        return output;
    }

    /**
     *
     * @param input
     * @return
     */
    public static String transformToUTF8(String input) {
        String output = input;
        for (Map.Entry<String, String> entry : TextResource.MAP_ENCODE_STEP_1.entrySet()) {
            String match = entry.getKey();
            String replacement = entry.getValue();
            if (output.contains(match)) {
                output = replaceAll(output, match, replacement);
            }
        }
        for (Map.Entry<String, String> entry : TextResource.MAP_ENCODE_STEP_2.entrySet()) {
            String match = entry.getKey();
            String replacement = entry.getValue();
            if (output.contains(match)) {
                output = replaceAll(output, match, replacement);
            }
        }
        char[] characters = output.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            Character character = characters[i];
            if (TextResource.MAP_SPECIAL_CHARS.containsKey(character)) {
                characters[i] = TextResource.MAP_SPECIAL_CHARS.get(character);
            }
        }

        return output;
    }

    public static String autoCorrectVowel(String text) {
        String input = text;
        for (Map.Entry<String, String> e : TextResource.VOWAL_AUTO_CORRECT_STEP_1.entrySet()) {
            String match = e.getKey();
            String replacement = e.getValue();
            if (input.contains(match)) {
                input = replaceAll(input, match, replacement);
            }
        }
        for (Map.Entry<String, String> e : TextResource.VOWAL_AUTO_CORRECT_STEP_2.entrySet()) {
            String match = e.getKey();
            String replacement = e.getValue();
            if (input.contains(match)) {
                input = replaceAll(input, match, replacement);
            }
        }
        for (Map.Entry<String, String> e : TextResource.VOWAL_AUTO_CORRECT_STEP_3.entrySet()) {
            String match = e.getKey();
            String replacement = e.getValue();
            if (input.contains(match)) {
                input = replaceAll(input, match, replacement);
            }
        }
        return input;
    }

    /**
     *
     * @param input
     * @return
     */
    public static String removeAccent(String input) {
        char[] characters = input.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            Character character = characters[i];
            if (TextResource.MAP_ACCENT_CHARACTERS.containsKey(character)) {
                characters[i] = TextResource.MAP_ACCENT_CHARACTERS.getOrDefault(character, character);
            }
        }
        return new String(characters);
    }

    public static boolean isMarks(char input) {
        return TextResource.MARKS.contains(input);
    }

    /**
     *
     * @param input : text need remove marks
     * @return
     */
    public static String removeMarks(String input) {
        char[] characters = input.toCharArray();
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < characters.length; i++) {
            char character = characters[i];
            if (!TextResource.MARKS.contains(character)) {
                list.add(character);
            }
        }
        characters = new char[list.size()];
        for (int i = 0; i < list.size(); i++) {
            Character character = list.get(i);
            characters[i] = character;
        }
        return new String(characters);
    }

    /**
     *
     * @param input : text need remove marks
     * @return
     */
    public static String splitMarks(String input) {
        char[] characters = input.toCharArray();
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < characters.length; i++) {
            char character = characters[i];
            if (TextResource.MARKS.contains(character)) {
                list.add(' ');
                list.add(character);
                list.add(' ');
            } else {
                list.add(character);
            }
        }
        characters = new char[list.size()];
        for (int i = 0; i < list.size(); i++) {
            Character character = list.get(i);
            characters[i] = character;
        }
        return new String(characters);
    }

    /**
     *
     * @param input : text need remove marks
     * @param replaceCharacter
     * @return
     */
    public static String replaceMarks(String input, char replaceCharacter) {
        char[] characters = input.toCharArray();
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < characters.length; i++) {
            char character = characters[i];
            if (TextResource.MARKS.contains(character)) {
                list.add(replaceCharacter);
            } else {
                list.add(character);
            }
        }
        characters = new char[list.size()];
        for (int i = 0; i < list.size(); i++) {
            Character character = list.get(i);
            characters[i] = character;
        }
        return new String(characters);
    }

    /**
     * replace all match text by replacement text
     *
     * @param input : input text need replace
     * @param match : text that input match
     * @param replacement : replace match text by replacement
     * @return
     */
    public static String replaceAll(String input, String match, String replacement) {
        char[] charInputs = input.toCharArray();
        char[] charMatchs = match.toCharArray();
        char[] charReplacements = replacement.toCharArray();
        List<Integer> indexes = TextPreprocessUtils.getIndexes(charInputs, charMatchs);
        char[] charOutputs = new char[charInputs.length - (charMatchs.length
                - charReplacements.length) * indexes.size()];

        int indexOutput = 0;
        for (int i = 0; i < charInputs.length;) {
            if (checkMatchAt(i, charInputs, charMatchs)) {
                System.arraycopy(charReplacements, 0, charOutputs, indexOutput,
                        charReplacements.length);
                i += charMatchs.length;
                indexOutput += charReplacements.length;
            } else {
                charOutputs[indexOutput] = charInputs[i];
                indexOutput++;
                i++;
            }
        }
        return new String(charOutputs);
    }

    /**
     *
     * @param index
     * @param charInputs
     * @param charMatchs
     * @return
     */
    public static boolean checkMatchAt(int index, char[] charInputs,
            char[] charMatchs) {
        if (charMatchs == null || charInputs == null || charMatchs.length == 0) {
            return false;
        }
        if (index + charMatchs.length > charInputs.length) {
            return false;
        }
        for (int i = 0; i < charMatchs.length; i++) {
            char charMatch = charMatchs[i];
            if (charMatch != charInputs[index + i]) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param charInputs
     * @param charMatchs
     * @return
     */
    public static List<Integer> getIndexes(char[] charInputs, char[] charMatchs) {
        List<Integer> indexes = new ArrayList<>();
        if (charMatchs == null || charInputs == null || charMatchs.length == 0
                || charMatchs.length > charInputs.length) {
            return indexes;
        }
        for (int i = 0; i <= charInputs.length - charMatchs.length;) {
            if (checkMatchAt(i, charInputs, charMatchs)) {
                indexes.add(i);
                i += charMatchs.length;
            } else {
                i++;
            }
        }
        return indexes;
    }

    /**
     *
     * @param input
     * @param match
     * @return
     */
    public static List<Integer> getIndexes(String input, String match) {
        return TextPreprocessUtils.getIndexes(input.toCharArray(), match.toCharArray());
    }

    /**
     *
     * @param input
     * @return
     */
    public static String reverse(String input) {
        return new StringBuffer(input).reverse().toString();
    }

    public static void main(String[] args) {
        String text = "tìm thuê nhà nguyên căn (gIá rẻ), tại Hà nôị".toLowerCase();
        System.out.println(text);
        text = splitMarks(text);
        System.out.println(text);
    }
}
