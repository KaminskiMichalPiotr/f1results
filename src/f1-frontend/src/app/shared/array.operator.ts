import {Identity} from "./models/identity.model";

export function changeElementIfPresentOrAdd<T extends Identity>(element: T, array: T[]): T[] {
  let index = array.findIndex(data => data.id === element.id)
  if (index !== -1) {
    array[index] = element;
    return [...array] as T[];
  } else
    return [...array, element] as T[];
}

export function deleteElement<T extends Identity>(identity: T, array: T[]): T[] {
  return array.filter(data => data.id !== identity.id);
}
