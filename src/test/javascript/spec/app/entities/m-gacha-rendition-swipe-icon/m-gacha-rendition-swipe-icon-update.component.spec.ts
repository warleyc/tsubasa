/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGachaRenditionSwipeIconUpdateComponent } from 'app/entities/m-gacha-rendition-swipe-icon/m-gacha-rendition-swipe-icon-update.component';
import { MGachaRenditionSwipeIconService } from 'app/entities/m-gacha-rendition-swipe-icon/m-gacha-rendition-swipe-icon.service';
import { MGachaRenditionSwipeIcon } from 'app/shared/model/m-gacha-rendition-swipe-icon.model';

describe('Component Tests', () => {
  describe('MGachaRenditionSwipeIcon Management Update Component', () => {
    let comp: MGachaRenditionSwipeIconUpdateComponent;
    let fixture: ComponentFixture<MGachaRenditionSwipeIconUpdateComponent>;
    let service: MGachaRenditionSwipeIconService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGachaRenditionSwipeIconUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MGachaRenditionSwipeIconUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MGachaRenditionSwipeIconUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MGachaRenditionSwipeIconService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MGachaRenditionSwipeIcon(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new MGachaRenditionSwipeIcon();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
