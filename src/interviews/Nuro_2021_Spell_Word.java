package interviews;

/**
 * Two players are given a dictionary with words made of lowercase letters only.
 * Each player takes turns adding a letter to a string. When a player adds a
 * letter that results in a string that is in the dictionary, that player wins.
 * Assuming that both players play optimally, write a program that returns which
 * player will win given a dictionary of words.
 *
 * Example:
 * dictionary = {"top", "that", "ten"}
 *
 * First player will have to choose t, so the current string is, str = "t".
 * Player 2 will not choose o or e because then player 1 can win on the next turn.
 * But if player 2 chooses h, then player 2 is guaranteed to win,
 * so player 2 chooses h, then player 1 chooses e, and player 2 chooses n,
 * and player 2 will have completed the word then, and thus be designated the winner.
 */
public class Nuro_2021_Spell_Word {

    // todo
    // https://leetcode.com/discuss/interview-question/1076867/first-player-to-spell-a-full-word-that-exists-within-a-given-dictionary-of-words-wins
}
