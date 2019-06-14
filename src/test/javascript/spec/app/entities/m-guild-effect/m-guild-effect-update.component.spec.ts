/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGuildEffectUpdateComponent } from 'app/entities/m-guild-effect/m-guild-effect-update.component';
import { MGuildEffectService } from 'app/entities/m-guild-effect/m-guild-effect.service';
import { MGuildEffect } from 'app/shared/model/m-guild-effect.model';

describe('Component Tests', () => {
  describe('MGuildEffect Management Update Component', () => {
    let comp: MGuildEffectUpdateComponent;
    let fixture: ComponentFixture<MGuildEffectUpdateComponent>;
    let service: MGuildEffectService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGuildEffectUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MGuildEffectUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MGuildEffectUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MGuildEffectService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MGuildEffect(123);
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
        const entity = new MGuildEffect();
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
