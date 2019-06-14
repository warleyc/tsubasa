/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGuildEffectLevelUpdateComponent } from 'app/entities/m-guild-effect-level/m-guild-effect-level-update.component';
import { MGuildEffectLevelService } from 'app/entities/m-guild-effect-level/m-guild-effect-level.service';
import { MGuildEffectLevel } from 'app/shared/model/m-guild-effect-level.model';

describe('Component Tests', () => {
  describe('MGuildEffectLevel Management Update Component', () => {
    let comp: MGuildEffectLevelUpdateComponent;
    let fixture: ComponentFixture<MGuildEffectLevelUpdateComponent>;
    let service: MGuildEffectLevelService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGuildEffectLevelUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MGuildEffectLevelUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MGuildEffectLevelUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MGuildEffectLevelService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MGuildEffectLevel(123);
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
        const entity = new MGuildEffectLevel();
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
