package created;

/**
 * 单例模式
 */
public class SingletonMode {
    /**
     * 1.懒汉式线程不安全写法
     */
    /*public class Singleton {
        private static Singleton instance;
        private Singleton (){}

        public static Singleton getInstance() {
            if (instance == null) {
                instance = new Singleton();
            }
            return instance;
        }
    }*/

    /**
     * 2.懒汉式线程安全写法
     */
    /*public class Singleton {
        private static Singleton instance;
        private Singleton (){}
        public static synchronized Singleton getInstance() {
            if (instance == null) {
                instance = new Singleton();
            }
            return instance;
        }
    }*/

    /**
     * 3.饿汉式
     */
    /*public class Singleton {
        private static Singleton instance = new Singleton();
        private Singleton (){}
        public static Singleton getInstance() {
            return instance;
        }
    }*/

    /**
     * 4.双检锁/双重校验锁（DCL，即 double-checked locking）
     * 这种方式采用双锁机制，安全且在多线程情况下能保持高性能。
     */
    /*public class Singleton {
        private volatile static Singleton singleton;
        private Singleton (){}
        public static Singleton getSingleton() {
            if (singleton == null) {
                synchronized (Singleton.class) {
                    if (singleton == null) {
                        singleton = new Singleton();
                    }
                }
            }
            return singleton;
        }
    }*/

    /**
     * 5.登记式/静态内部类
     * 是否 Lazy 初始化：是
     * 是否多线程安全：是
     * 实现难度：一般
     * 描述：这种方式能达到双检锁方式一样的功效，但实现更简单。
     * 对静态域使用延迟初始化，应使用这种方式而不是双检锁方式。
     * 这种方式只适用于静态域的情况，双检锁方式可在实例域需要延迟初始化时使用。
     * 这种方式同样利用了 classloader 机制来保证初始化 instance 时只有一个线程，
     * 它跟第 3 种方式不同的是：第 3 种方式只要 Singleton 类被装载了，
     * 那么 instance 就会被实例化（没有达到 lazy loading 效果），而这种方式是 Singleton 类被装载了，instance 不一定被初始化。
     * 因为 SingletonHolder 类没有被主动使用，只有通过显式调用 getInstance 方法时，才会显式装载 SingletonHolder 类，
     * 从而实例化 instance。想象一下，如果实例化 instance 很消耗资源，所以想让它延迟加载，另外一方面，
     * 又不希望在 Singleton 类加载时就实例化，因为不能确保 Singleton 类还可能在其他的地方被主动使用从而被加载，
     * 那么这个时候实例化 instance 显然是不合适的。这个时候，这种方式相比第 3 种方式就显得很合理。
     */
    /*public class Singleton {
        private static class SingletonHolder {
            private static final Singleton INSTANCE = new Singleton();
        }
        private Singleton (){}
        public static final Singleton getInstance() {
            return SingletonHolder.INSTANCE;
        }
    }*/

    /**
     * 6、枚举
     * 是否 Lazy 初始化：否
     * 是否多线程安全：是
     * 实现难度：易
     * 描述：这种实现方式还没有被广泛采用，但这是实现单例模式的最佳方法。它更简洁，自动支持序列化机制，绝对防止多次实例化。
     * 这种方式是 Effective Java 作者 Josh Bloch 提倡的方式，它不仅能避免多线程同步问题，而且还自动支持序列化机制，防止反序列化重新创建新的对象，绝对防止多次实例化。不过，由于 JDK1.5 之后才加入 enum 特性，用这种方式写不免让人感觉生疏，在实际工作中，也很少用。
     * 不能通过 reflection attack 来调用私有构造方法。
     */
    /*public enum Singleton {
        INSTANCE;
        public void whateverMethod() {
        }
    }*/

    /**
     * 经验之谈：一般情况下，不建议使用第 1 种和第 2 种懒汉方式，建议使用第 3 种饿汉方式。
     * 只有在要明确实现 lazy loading 效果时，才会使用第 5 种登记方式。如果涉及到反序列化创建对象时，可以尝试使用第 6 种枚举方式。
     * 如果有其他特殊的需求，可以考虑使用第 4 种双检锁方式。*/
}
