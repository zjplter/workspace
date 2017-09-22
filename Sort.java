import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sort {

	// 基数排序
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void sort(int[] array) {
		// 首先确定排序的趟数;
		int max = array[0];
		for (int i = 1; i < array.length; i++) {
			if (array[i] > max) {
				max = array[i];
			}
		}
		int time = 0;
		// 判断位数;
		while (max > 0) {
			max /= 10;
			time++;
		}
		// 建立10个队列;
		List<ArrayList> queue = new ArrayList<ArrayList>();
		for (int i = 0; i < 10; i++) {
			ArrayList<Integer> queue1 = new ArrayList<Integer>();
			queue.add(queue1);
		}
		// 进行time次分配和收集;
		for (int i = 0; i < time; i++) {
			// 分配数组元素;
			for (int j = 0; j < array.length; j++) {
				// 得到数字的第time+1位数;
				int x = array[j] % (int) Math.pow(10, i + 1)
						/ (int) Math.pow(10, i);
				ArrayList<Integer> queue2 = queue.get(x);
				queue2.add(array[j]);
				queue.set(x, queue2);
			}
			int count = 0;// 元素计数器;
			// 收集队列元素;
			for (int k = 0; k < 10; k++) {
				while (queue.get(k).size() > 0) {
					ArrayList<Integer> queue3 = queue.get(k);
					array[count] = queue3.get(0);
					queue3.remove(0);
					count++;
				}
			}
		}
	}

	// 归并排序
	public static void mergeSort(int[] numbers, int left, int right) {
		int t = 1;// 每组元素个数
		int size = right - left + 1;
		while (t < size) {
			int s = t;// 本次循环每组元素个数
			t = 2 * s;
			int i = left;
			while (i + (t - 1) < size) {
				merge(numbers, i, i + (s - 1), i + (t - 1));
				i += t;
			}
			if (i + (s - 1) < right)
				merge(numbers, i, i + (s - 1), right);
		}
	}

	private static void merge(int[] data, int p, int q, int r) {
		int[] B = new int[data.length];
		int s = p;
		int t = q + 1;
		int k = p;
		while (s <= q && t <= r) {
			if (data[s] <= data[t]) {
				B[k] = data[s];
				s++;
			} else {
				B[k] = data[t];
				t++;
			}
			k++;
		}
		if (s == q + 1)
			B[k++] = data[t++];
		else
			B[k++] = data[s++];
		for (int i = p; i <= r; i++)
			data[i] = B[i];
	}

	// 快速排序
	public static void quickSort(int[] numbers, int start, int end) {
		if (start < end) {
			int base = numbers[start]; // 选定的基准值（第一个数值作为基准值）
			int temp; // 记录临时中间值
			int i = start, j = end;
			do {
				while ((numbers[i] < base) && (i < end))
					i++;
				while ((numbers[j] > base) && (j > start))
					j--;
				if (i <= j) {
					temp = numbers[i];
					numbers[i] = numbers[j];
					numbers[j] = temp;
					i++;
					j--;
				}
			} while (i <= j);
			if (start < j)
				quickSort(numbers, start, j);
			if (end > i)
				quickSort(numbers, i, end);
		}
	}

	// 冒泡排序
	public void bubbleSort(int[] a) {
		int temp;
		for (int i = 0, len = a.length; i < len; i++) {
			for (int j = 0; j < a.length - i - 1; j++) {
				if (a[j] > a[j + 1]) {
					temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
	}

	// 堆排序
	public void heapSort(int[] a) {
		System.out.println("开始排序");
		int arrayLength = a.length;
		// 循环建堆
		for (int i = 0; i < arrayLength - 1; i++) {
			// 建堆

			buildMaxHeap(a, arrayLength - 1 - i);
			// 交换堆顶和最后一个元素
			swap(a, 0, arrayLength - 1 - i);
			System.out.println(Arrays.toString(a));
		}
	}

	private void swap(int[] data, int i, int j) {
		int tmp = data[i];
		data[i] = data[j];
		data[j] = tmp;
	}

	// 对data数组从0到lastIndex建大顶堆
	private void buildMaxHeap(int[] data, int lastIndex) {
		// 从lastIndex处节点（最后一个节点）的父节点开始
		for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
			// k保存正在判断的节点
			int k = i;
			// 如果当前k节点的子节点存在
			while (k * 2 + 1 <= lastIndex) {
				// k节点的左子节点的索引
				int biggerIndex = 2 * k + 1;
				// 如果biggerIndex小于lastIndex，即biggerIndex+1代表的k节点的右子节点存在
				if (biggerIndex < lastIndex) {
					// 若果右子节点的值较大
					if (data[biggerIndex] < data[biggerIndex + 1]) {
						// biggerIndex总是记录较大子节点的索引
						biggerIndex++;
					}
				}
				// 如果k节点的值小于其较大的子节点的值
				if (data[k] < data[biggerIndex]) {
					// 交换他们
					swap(data, k, biggerIndex);
					// 将biggerIndex赋予k，开始while循环的下一次循环，重新保证k节点的值大于其左右子节点的值
					k = biggerIndex;
				} else {
					break;
				}
			}
		}
	}

	// 简单选择排序
	public void selectSort(int[] a) {
		int length = a.length;
		for (int i = 0; i < length; i++) {// 循环次数
			int key = a[i];
			int position = i;
			for (int j = i + 1; j < length; j++) {// 选出最小的值和位置
				if (a[j] < key) {
					key = a[j];
					position = j;
				}
			}
			a[position] = a[i];// 交换位置
			a[i] = key;
		}
	}

	// 希尔排序
	public void sheelSort(int[] a) {
		int d = a.length;
		while (d != 0) {
			d = d / 2;
			for (int x = 0; x < d; x++) {// 分的组数
				for (int i = x + d; i < a.length; i += d) {// 组中的元素，从第二个数开始
					int j = i - d;// j为有序序列最后一位的位数
					int temp = a[i];// 要插入的元素
					for (; j >= 0 && temp < a[j]; j -= d) {// 从后往前遍历。
						a[j + d] = a[j];// 向后移动d位
					}
					a[j + d] = temp;
				}
			}
		}
	}

	// 直接插入排序
	public void insertSort(int[] a) {
		int insertNum;
		for (int i = 0, len = a.length; i < len; i++) {
			insertNum = a[i];// 要插入的数
			int j = i - 1;// 已经排序好的序列元素个数
			while (j >= 0 && a[j] > insertNum) {// 序列从后到前循环，将大于insertNum的数向后移动一格
				a[j + 1] = a[j];// 元素移动一格
				j--;
			}
			a[j + 1] = insertNum;// 将需要插入的数放在要插入的位置。
		}
	}

	public static void main(String[] args) {

		Sort sort = new Sort();

		int[] a = { 1, 4, 3, 9, 2, 5, 7 };

		sort.insertSort(a);

		StringBuilder sbString = new StringBuilder();

		for (int i : a) {
			sbString.append(i).append(",");
		}

		String print = "";

		if (sbString.toString().endsWith(",")) {
			print = sbString.substring(0, sbString.length() - 1);
		}

		System.out.println(print);
	}

}
