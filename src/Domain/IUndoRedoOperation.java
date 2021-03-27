package Domain;

public interface IUndoRedoOperation {
    void doUndo() throws Exception;
    void doRedo() throws Exception;
}
