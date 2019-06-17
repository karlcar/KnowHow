package com.karl.model;

import com.karl.service.BusinessSocketService;

public class Weather {
		private String high;  //����¶�
		private String low;
		private String type;
		private String notice;
		private String city;
		private String date;
		
		
		public String getHigh() {
			return high;
		}

		public void setHigh(String high) {
			this.high = high;
		}

		public String getLow() {
			return low;
		}

		public void setLow(String low) {
			this.low = low;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getNotice() {
			return notice;
		}

		public void setNotice(String notice) {
			this.notice = notice;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		@Override
		public String toString() {
			String content = "";
			content += "����:" + city + BusinessSocketService.enter;
			content += "����:" + date + BusinessSocketService.enter;
			content += "����¶�:" + high + BusinessSocketService.enter;
			content += "����¶�:" + low + BusinessSocketService.enter;
			content += "����:" + type + BusinessSocketService.enter;
			content += "ע������:" + notice + BusinessSocketService.enter;
		return content;
		}
}
