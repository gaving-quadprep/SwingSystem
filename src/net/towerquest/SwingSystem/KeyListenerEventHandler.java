package net.towerquest.SwingSystem;

import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

import net.towerquest.engine.system.KeyboardEventHandler;
import static net.towerquest.engine.system.KeyboardEventHandler.KeyCode.*;
import net.towerquest.engine.util.BiHashMap;
import net.towerquest.engine.util.BiMap;

public class KeyListenerEventHandler implements KeyboardEventHandler, KeyListener {
	private Consumer<KeyCode> onKeyPressed = null, onKeyReleased = null;
	private Consumer<Character> onKeyTyped = null;
	private Set<KeyCode> heldKeys = new HashSet<KeyCode>(),
			changedKeys = new HashSet<KeyCode>();
	BiMap<Integer, KeyCode> keyCodes = new BiHashMap<Integer, KeyCode>();
	
	KeyListenerEventHandler() {
		keyCodes.put(VK_A, KEY_A);
		keyCodes.put(VK_B, KEY_B);
		keyCodes.put(VK_C, KEY_C);
		keyCodes.put(VK_D, KEY_D);
		keyCodes.put(VK_E, KEY_E);
		keyCodes.put(VK_F, KEY_F);
		keyCodes.put(VK_G, KEY_G);
		keyCodes.put(VK_H, KEY_H);
		keyCodes.put(VK_I, KEY_I);
		keyCodes.put(VK_J, KEY_J);
		keyCodes.put(VK_K, KEY_K);
		keyCodes.put(VK_L, KEY_L);
		keyCodes.put(VK_M, KEY_M);
		keyCodes.put(VK_N, KEY_N);
		keyCodes.put(VK_O, KEY_O);
		keyCodes.put(VK_P, KEY_P);
		keyCodes.put(VK_Q, KEY_Q);
		keyCodes.put(VK_R, KEY_R);
		keyCodes.put(VK_S, KEY_S);
		keyCodes.put(VK_T, KEY_T);
		keyCodes.put(VK_U, KEY_U);
		keyCodes.put(VK_V, KEY_V);
		keyCodes.put(VK_W, KEY_W);
		keyCodes.put(VK_X, KEY_X);
		keyCodes.put(VK_Y, KEY_Y);
		keyCodes.put(VK_Z, KEY_Z);

		keyCodes.put(VK_0, KEY_0);
		keyCodes.put(VK_1, KEY_1);
		keyCodes.put(VK_2, KEY_2);
		keyCodes.put(VK_3, KEY_3);
		keyCodes.put(VK_4, KEY_4);
		keyCodes.put(VK_5, KEY_5);
		keyCodes.put(VK_6, KEY_6);
		keyCodes.put(VK_7, KEY_7);
		keyCodes.put(VK_8, KEY_8);
		keyCodes.put(VK_9, KEY_9);

		keyCodes.put(VK_F1, KEY_F1);
		keyCodes.put(VK_F2, KEY_F2);
		keyCodes.put(VK_F3, KEY_F3);
		keyCodes.put(VK_F4, KEY_F4);
		keyCodes.put(VK_F5, KEY_F5);
		keyCodes.put(VK_F6, KEY_F6);
		keyCodes.put(VK_F7, KEY_F7);
		keyCodes.put(VK_F8, KEY_F8);
		keyCodes.put(VK_F9, KEY_F9);
		keyCodes.put(VK_F10, KEY_F10);
		keyCodes.put(VK_F11, KEY_F11);
		keyCodes.put(VK_F12, KEY_F12);

		keyCodes.put(VK_UP, KeyCode.KEY_UP);
		keyCodes.put(VK_DOWN, KeyCode.KEY_DOWN);
		keyCodes.put(VK_LEFT, KeyCode.KEY_LEFT);
		keyCodes.put(VK_RIGHT, KeyCode.KEY_RIGHT);
	
		// TODO finish
	}
	
	// KeyListener methods
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		KeyCode translated = keyCodes.get(arg0.getKeyCode());
		heldKeys.add(translated);
		changedKeys.add(translated);
		if(onKeyPressed != null)
			onKeyPressed.accept(translated);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		KeyCode translated = keyCodes.get(arg0.getKeyCode());
		heldKeys.remove(translated);
		changedKeys.add(translated);
		if(onKeyReleased != null)
			onKeyReleased.accept(translated);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		if(onKeyTyped != null)
			onKeyTyped.accept(arg0.getKeyChar());
	}
	
	// KeyEventHandler methods

	@Override
	public void update() {
		// TODO Auto-generated method stub
		changedKeys.clear();
	}

	@Override
	public void onKeyPressed(Consumer<KeyCode> fn) {
		onKeyPressed = fn;
	}

	@Override
	public void onKeyReleased(Consumer<KeyCode> fn) {
		onKeyReleased = fn;
	}

	@Override
	public void onKeyTyped(Consumer<Character> fn) {
		onKeyTyped = fn;
	}

	@Override
	public boolean isKeyDown(KeyCode key) {
		return heldKeys.contains(key);
	}

	@Override
	public Set<KeyCode> getChangedKeys() {
		return new HashSet<KeyCode>(changedKeys);
	}

}
