package com.wt.plugin.aspect

/**
 * 1.0.1 update open/close control
 */
class AopExtension {

  def enabled = true

  def getEnabled() {
    return enabled
  }

  def setEnabled(def enabled) {
    this.enabled = enabled
  }
}