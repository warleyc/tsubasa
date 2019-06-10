/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGachaRenditionAfterInputCutInTextColorUpdateComponent } from 'app/entities/m-gacha-rendition-after-input-cut-in-text-color/m-gacha-rendition-after-input-cut-in-text-color-update.component';
import { MGachaRenditionAfterInputCutInTextColorService } from 'app/entities/m-gacha-rendition-after-input-cut-in-text-color/m-gacha-rendition-after-input-cut-in-text-color.service';
import { MGachaRenditionAfterInputCutInTextColor } from 'app/shared/model/m-gacha-rendition-after-input-cut-in-text-color.model';

describe('Component Tests', () => {
  describe('MGachaRenditionAfterInputCutInTextColor Management Update Component', () => {
    let comp: MGachaRenditionAfterInputCutInTextColorUpdateComponent;
    let fixture: ComponentFixture<MGachaRenditionAfterInputCutInTextColorUpdateComponent>;
    let service: MGachaRenditionAfterInputCutInTextColorService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGachaRenditionAfterInputCutInTextColorUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MGachaRenditionAfterInputCutInTextColorUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MGachaRenditionAfterInputCutInTextColorUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MGachaRenditionAfterInputCutInTextColorService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MGachaRenditionAfterInputCutInTextColor(123);
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
        const entity = new MGachaRenditionAfterInputCutInTextColor();
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
