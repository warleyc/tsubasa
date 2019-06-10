/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Data } from '@angular/router';

import { TsubasaTestModule } from '../../../test.module';
import { MGachaRenditionAfterInputCutInTextColorComponent } from 'app/entities/m-gacha-rendition-after-input-cut-in-text-color/m-gacha-rendition-after-input-cut-in-text-color.component';
import { MGachaRenditionAfterInputCutInTextColorService } from 'app/entities/m-gacha-rendition-after-input-cut-in-text-color/m-gacha-rendition-after-input-cut-in-text-color.service';
import { MGachaRenditionAfterInputCutInTextColor } from 'app/shared/model/m-gacha-rendition-after-input-cut-in-text-color.model';

describe('Component Tests', () => {
  describe('MGachaRenditionAfterInputCutInTextColor Management Component', () => {
    let comp: MGachaRenditionAfterInputCutInTextColorComponent;
    let fixture: ComponentFixture<MGachaRenditionAfterInputCutInTextColorComponent>;
    let service: MGachaRenditionAfterInputCutInTextColorService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGachaRenditionAfterInputCutInTextColorComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: {
              data: {
                subscribe: (fn: (value: Data) => void) =>
                  fn({
                    pagingParams: {
                      predicate: 'id',
                      reverse: false,
                      page: 0
                    }
                  })
              }
            }
          }
        ]
      })
        .overrideTemplate(MGachaRenditionAfterInputCutInTextColorComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MGachaRenditionAfterInputCutInTextColorComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MGachaRenditionAfterInputCutInTextColorService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new MGachaRenditionAfterInputCutInTextColor(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.mGachaRenditionAfterInputCutInTextColors[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new MGachaRenditionAfterInputCutInTextColor(123)],
            headers
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.mGachaRenditionAfterInputCutInTextColors[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should not load a page is the page is the same as the previous page', () => {
      spyOn(service, 'query').and.callThrough();

      // WHEN
      comp.loadPage(0);

      // THEN
      expect(service.query).toHaveBeenCalledTimes(0);
    });

    it('should re-initialize the page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new MGachaRenditionAfterInputCutInTextColor(123)],
            headers
          })
        )
      );

      // WHEN
      comp.loadPage(1);
      comp.clear();

      // THEN
      expect(comp.page).toEqual(0);
      expect(service.query).toHaveBeenCalledTimes(2);
      expect(comp.mGachaRenditionAfterInputCutInTextColors[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('should calculate the sort attribute for an id', () => {
      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,desc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // GIVEN
      comp.predicate = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,desc', 'id']);
    });
  });
});
