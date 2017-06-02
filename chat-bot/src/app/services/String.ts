import {isNullOrUndefined} from "util";
export class String {
  static isNullOrEmpty(value: string): boolean {
    return isNullOrUndefined(value) || value.length === 0;
  }

  static isNullOrEmptyTrim(value: string): boolean {
    return isNullOrUndefined(value) || value.trim().length === 0;
  }
}
