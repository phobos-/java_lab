package pl.edu.pwr.msg;

public enum Type {
    URGENT("d1"), NORMAL("d2"), LOW("d3");

    private String desc;

    Type(String desc) {
        this.desc = desc;
    }

    public static Type getByDesc(String desc) throws NoMessageTypeFoundException {
        for (Type t : Type.values())
            if (t.desc.equals(desc))
                return t;
        throw new NoMessageTypeFoundException("no message found");
    }
}
