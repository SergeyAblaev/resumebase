/**
 * com.urise.webapp.model.Resume class
 */
public class Resume {

    // Unique identifier
    String uuid;

    @Override // вау! это переопределили типовой метод - чтобы в принт вывелось красиво!
    // попробуй ЗАКОММЕНТИТЬ его потом!
    public String toString() {
        return uuid;
    }
}
