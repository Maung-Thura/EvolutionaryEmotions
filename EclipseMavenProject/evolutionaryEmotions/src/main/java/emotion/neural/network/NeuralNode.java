package emotion.neural.network;

/**
 * Generic neural node that takes an input, processes and returns an output
 */
interface NeuralNode<I, O> {
	O process(I input);
}
