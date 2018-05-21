package org.peterkwan.udacity.joker;

import java.util.Random;

public class JokeProvider {

    private static final String[] JOKES = {
        "Doc, I can't stop singing the 'Green Green Grass of Home'. He said: 'That sounds like Tom Jones syndrome'. 'Is it common?'I asked.  'It's not unusual' he replied. ",
        "I went to the zoo the other day, there was only one dog in it, it was a shitzu.",
        "Dyslexic man walks into a bra",
        "I'm on a whiskey diet. I've lost three days already. ",
        "My therapist says I have a preoccupation with vengeance. We'll see about that.",
        "Slept like a log last night…….. Woke up in the fireplace.",
        "Went to the paper shop – it had blown away.",
        "I backed a horse last week at ten to one.  It came in at quarter past four. "
    };

    public static final String provideJoke() {
        return JOKES[new Random().nextInt(JOKES.length)];
    }
}
