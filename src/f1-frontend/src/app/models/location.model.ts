export interface Location {

  id: number | null;
  location: string;
  locationTag: string;
  country: string;

}


export function emptyLocation(): Location {
  return {id: null, location: '', locationTag: '', country: ''}
}
