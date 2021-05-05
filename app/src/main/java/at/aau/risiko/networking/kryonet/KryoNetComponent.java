package at.aau.risiko.networking.kryonet;

public interface KryoNetComponent {

    /**
     * Register a class for serialization.
     *
     * @param c
     */
    void registerClass(Class c);

}
