package controller;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class CtrlCompilacao {
	public static void main(String[] args) {
		// 1. Obter o compilador do sistema
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

		if (compiler == null) {
			System.err.println("JDK não encontrado. Use um JDK em vez de JRE.");
			return;
		}

		// 2. Criar um coletor para capturar erros e avisos (opcional)
		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();

		// 3. Obter o gerenciador de arquivos padrão
		try (StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null)) {

			// 4. Definir o arquivo .java a ser compilado
			File sourceFile = new File("C:/Temp/controller/HelloWorld.java");
			Iterable<? extends JavaFileObject> compilationUnits = fileManager
					.getJavaFileObjectsFromFiles(Arrays.asList(sourceFile));

			// 5. Criar e executar a tarefa de compilação
			// Argumentos: (Writer para log, FileManager, DiagnosticListener, Opções,
			// Classes para processamento de anotação, Unidades de compilação)
			JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, null, null,
					compilationUnits);

			boolean success = task.call();

			if (success) {
				System.out.println("Compilação concluída com sucesso!");
				 // 1. Define the location (directory or JAR file)
	            File file = new File("C:/Temp");

	            // 2. Convert the file to a URL format
	            URL url = file.toURI().toURL();
	            URL[] urls = new URL[]{url};

	            // 3. Create a URLClassLoader
	            // This loader will look for classes in the specified URL, separate from the system classpath
	            ClassLoader classLoader = new URLClassLoader(urls);

	            // 4. Load the class using its fully qualified name
	            Class<?> loadedClass = classLoader.loadClass("controller.HelloWorld");
	            System.out.println("Class loaded from: " + loadedClass.getProtectionDomain().getCodeSource().getLocation());

	            // 5. Instantiate and use the class (example using reflection to call a method)
	            Object instance = loadedClass.getDeclaredConstructor().newInstance();
	            
	            Method method = loadedClass.getDeclaredMethod("main", String[].class);
	            System.out.println(method);
	            
	            // If the method is private, make it accessible
	            method.setAccessible(true);

	            String[] param = new String[0];
	            Object[] invokeArgs = new Object[] { param };
	            
	            // 3. Invoke the method, passing null for the instance
	            Object result = method.invoke(null, invokeArgs);

			} else {
				// Listar erros encontrados
				for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics.getDiagnostics()) {
					System.err.format("Erro na linha %d em %s: %s%n", diagnostic.getLineNumber(),
							diagnostic.getSource().toUri(), diagnostic.getMessage(null));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}