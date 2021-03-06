import {formatDate} from "@angular/common";

export function convertToDate(date: string): Date {
  let day = date.slice(0, 2);
  let month = date.slice(3, 5);
  let year = date.slice(6, 10);
  let dateToConvert = year + '-' + month + '-' + day;
  return new Date(dateToConvert);
}

export function convertDateToString(date: Date): string {
  return formatDate(date, 'dd-MM-yyyy', 'en')
}
