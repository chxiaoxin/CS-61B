public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        LinkedListDeque<Character> linkeddq = new LinkedListDeque<>();
        for (int i=0; i<word.length(); i++){
            linkeddq.addLast(word.charAt(i));
        }
        return linkeddq;
    }

    private boolean recHelper(Deque dq){
        if (dq.isEmpty()||dq.size()==1) {
            return true;
        } else {
            if (dq.removeLast() == dq.removeFirst()) {
                return recHelper(dq);
            } else {
                return false;
            }
        }
    }

    public boolean isPalindrome(String word){
        return recHelper(wordToDeque(word));
    }

    private boolean recHelperComparator(Deque dq, CharacterComparator cc){
        if (dq.isEmpty()||dq.size()==1) {
            return true;
        } else {
            if (cc.equalChars((Character) dq.removeLast(),(Character) dq.removeFirst())) {
                return recHelperComparator(dq, cc);
            } else {
                return false;
            }
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        return recHelperComparator(wordToDeque(word), new OffByOne());
    }
}
