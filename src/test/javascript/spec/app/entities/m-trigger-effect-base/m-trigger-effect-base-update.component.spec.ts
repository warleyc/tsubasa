/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTriggerEffectBaseUpdateComponent } from 'app/entities/m-trigger-effect-base/m-trigger-effect-base-update.component';
import { MTriggerEffectBaseService } from 'app/entities/m-trigger-effect-base/m-trigger-effect-base.service';
import { MTriggerEffectBase } from 'app/shared/model/m-trigger-effect-base.model';

describe('Component Tests', () => {
  describe('MTriggerEffectBase Management Update Component', () => {
    let comp: MTriggerEffectBaseUpdateComponent;
    let fixture: ComponentFixture<MTriggerEffectBaseUpdateComponent>;
    let service: MTriggerEffectBaseService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTriggerEffectBaseUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MTriggerEffectBaseUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MTriggerEffectBaseUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MTriggerEffectBaseService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MTriggerEffectBase(123);
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
        const entity = new MTriggerEffectBase();
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
