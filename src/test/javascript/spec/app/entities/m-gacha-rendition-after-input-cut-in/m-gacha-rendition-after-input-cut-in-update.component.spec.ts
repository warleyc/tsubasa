/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGachaRenditionAfterInputCutInUpdateComponent } from 'app/entities/m-gacha-rendition-after-input-cut-in/m-gacha-rendition-after-input-cut-in-update.component';
import { MGachaRenditionAfterInputCutInService } from 'app/entities/m-gacha-rendition-after-input-cut-in/m-gacha-rendition-after-input-cut-in.service';
import { MGachaRenditionAfterInputCutIn } from 'app/shared/model/m-gacha-rendition-after-input-cut-in.model';

describe('Component Tests', () => {
  describe('MGachaRenditionAfterInputCutIn Management Update Component', () => {
    let comp: MGachaRenditionAfterInputCutInUpdateComponent;
    let fixture: ComponentFixture<MGachaRenditionAfterInputCutInUpdateComponent>;
    let service: MGachaRenditionAfterInputCutInService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGachaRenditionAfterInputCutInUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MGachaRenditionAfterInputCutInUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MGachaRenditionAfterInputCutInUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MGachaRenditionAfterInputCutInService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MGachaRenditionAfterInputCutIn(123);
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
        const entity = new MGachaRenditionAfterInputCutIn();
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
