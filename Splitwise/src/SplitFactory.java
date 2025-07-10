public class SplitFactory {

    public static Split createSplit(SplitType splitType){
        switch (splitType){
            case EQUAL -> {
                return new EqualSplit();
            }
            case PERCENTAGE -> {
                return new PercenatgeSplit();
            }
            default ->
                throw new IllegalArgumentException("Unknown split type: " + splitType);
        }
    }
}
