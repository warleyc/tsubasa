/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MEncountersBonusUpdateComponent } from 'app/entities/m-encounters-bonus/m-encounters-bonus-update.component';
import { MEncountersBonusService } from 'app/entities/m-encounters-bonus/m-encounters-bonus.service';
import { MEncountersBonus } from 'app/shared/model/m-encounters-bonus.model';

describe('Component Tests', () => {
  describe('MEncountersBonus Management Update Component', () => {
    let comp: MEncountersBonusUpdateComponent;
    let fixture: ComponentFixture<MEncountersBonusUpdateComponent>;
    let service: MEncountersBonusService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MEncountersBonusUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MEncountersBonusUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MEncountersBonusUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MEncountersBonusService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MEncountersBonus(123);
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
        const entity = new MEncountersBonus();
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
