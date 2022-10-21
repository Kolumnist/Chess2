package de.hhn.it.devtools.apis.duckhunt;

/**
 * Allows menu ui interaction.
 */
public interface DuckHuntMenuService {
  Integer id = null;

  void onButtonClicked(int buttonId);
}
