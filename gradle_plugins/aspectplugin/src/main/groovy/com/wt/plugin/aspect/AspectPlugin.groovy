package com.wt.plugin.aspect

import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryPlugin
import org.aspectj.bridge.IMessage
import org.aspectj.bridge.MessageHandler
import org.aspectj.tools.ajc.Main
import org.gradle.api.Plugin
import org.gradle.api.Project

class AspectPlugin implements Plugin<Project> {

  @Override
  void apply(Project project) {

    project.dependencies {
      implementation 'org.aspectj:aspectjrt:1.9.5'
    }

    def log = project.logger
    log.info '======================================'
    log.info '=========AspectPlugin Working========='
    log.info '======================================'

    def hasApp = project.plugins.withType(AppPlugin)
    def hasLib = project.plugins.withType(LibraryPlugin)
    if (!hasApp && !hasLib) {
      throw new IllegalStateException("'android' or 'android-library' plugin required.")
    }

    final def variants
    if (hasApp) {
      variants = project.android.applicationVariants
    } else {
      variants = project.android.libraryVariants
    }

    project.extensions.create("aop", AopExtension.class)

    variants.all { variant ->

      if (!project.aop.enabled) {
        log.debug("aop is not disabled.")
        return
      }

      // Version 1.0.1 Changed : INFO: API 'variant.getJavaCompile()' is obsolete and has been replaced with 'variant.getJavaCompileProvider()'.
      def javaCompile = variant.getJavaCompileProvider().get()
      javaCompile.doLast {
        String[] args = ["-showWeaveInfo",
            "-1.8",
            "-inpath", javaCompile.destinationDir.toString(),
            "-aspectpath", javaCompile.classpath.asPath,
            "-d", javaCompile.destinationDir.toString(),
            "-classpath", javaCompile.classpath.asPath,
            "-bootclasspath", project.android.bootClasspath.join(File.pathSeparator)]
        MessageHandler handler = new MessageHandler(true)
        def main = new Main()
        main.run(args, handler)

        for (IMessage message : handler.getMessages(null, true)) {
          switch (message.getKind()) {
            case IMessage.ABORT:
            case IMessage.ERROR:
            case IMessage.FAIL:
              log.error message.message, message.thrown
              throw message.thrown
              break
            case IMessage.WARNING:
            case IMessage.INFO:
              log.info message.message, message.thrown
              break
            case IMessage.DEBUG:
              log.debug message.message, message.thrown
              break
          }
        }
        main.quit()
      }
    }
  }
}