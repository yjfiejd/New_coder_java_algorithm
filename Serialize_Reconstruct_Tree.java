import java.util.LinkedList;
import java.util.Queue;

// 【问题：】序列化与反序列化二叉树
// 如何报树的结构保存在内存中，关机后，这些头结点指针消失，可以通过一些序列来表示，重新开机时候，可以通过这些序列来还原
// 

// 先序遍历的 ---【序列化】
// 举例：二叉树构建1，2，3，4，5，6，7  -> 序列化的形式(指向空也要表达出来)
// 如果用先序遍历来记录，用字符串表达：str表示：1_2_4_#_#_5_#_#_3_6_#_#_7_#_#_
// 不一定用下划线“_”空占位符，可以用“!”等其他的符号来表达。，
// -> （需要空占位符#）如果没有这些特殊符号表达空，那么111（全为左），111（全为右），则无法区分
// -> （需要下划线，或者感叹号连接） -> 不占会乱 , 1,23（1_23）, 和 1，2，3(1_2_3)，去了下划线，无法区分 
//【反序列化】
// java Queue中 remove/poll, add/offer, element/peek区别: https://blog.csdn.net/ustcjackylau/article/details/42454779
//
//
//
//

public class Serialize_Reconstruct_Tree {

	// 初始化
	public static class Node{
		public int value;
		public Node left;
		public Node right;
		public Node parent;
		
		public Node(int data) {
			this.value = data;
		}	
	}
	
	// 【序列化】通过先序遍历用字符串来储存(采用递归) - 序列化 —》先中再左再右 
	//  举例 树1，2， 3， 4，5  -> 1_2_4_#_#_#_3_#_5_#_#_
	public static String serialByPrd(Node head) {
		if (head == null) {
			return "#!"; // 如果遇到节点为空，用"#"来表示。
		}
		String res = head.value + "!"; // 如果节点不为空，当前节点value+！符号
		res += serialByPrd(head.left); // 这个节点左树返回一个字符串
		res += serialByPrd(head.right); // 这个节点右树返回一个字符串
		return res;
	}
	
	// 【反序列化】 -> 通过一个字符串形成二叉树
	// 队列的add（）方法和offer（）方法的区别：区别：两者都是往队列尾部插入元素，不同的时候，当超出队列界限的时候，add（）方法是抛出异常让你处理，而offer（）方法是直接返回false
	public static Node reconByPreString(String preStr) {
		// 1）先把字符串通过刚才的规则"!"拆开,变为数组，储存在y一个队列中
		String[] values = preStr.split("!");
		Queue<String> queue = new LinkedList<String>(); // 创建一个队列，里面存String
		for (int i = 0; i != values.length; i++) {
			queue.offer(values[i]); // 依次把String中的数值，填入队列
		}
		// 2）队列中数字序列存好后，使用函数生成二叉树
		return reconPreOrder(queue);
	}
	
	public static Node reconPreOrder(Queue<String> queue) {
		// 3） 队列拿出一个东西，成为String，
		String value = queue.poll(); // 队列弹出一个数，消费这个队列
		if (value.equals("#")) { // 遇到“#”，就是表示为空节点
			return null;
		}
		Node head = new Node(Integer.valueOf(value));
		head.left = reconPreOrder(queue); // 整个左子树，给递归这个过程，消费这个队列
		head.right = reconPreOrder(queue);
		return head; // 返回这个头结点，其实就是整棵树返回了。
	}
	
	
	
	
	public static void main(String[] args) {
//		Node head = new Node(1);
//		System.out.println(serialByPrd(head));
		
		
	}

}
