/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MPowerupActionSkillCostUpdateComponent } from 'app/entities/m-powerup-action-skill-cost/m-powerup-action-skill-cost-update.component';
import { MPowerupActionSkillCostService } from 'app/entities/m-powerup-action-skill-cost/m-powerup-action-skill-cost.service';
import { MPowerupActionSkillCost } from 'app/shared/model/m-powerup-action-skill-cost.model';

describe('Component Tests', () => {
  describe('MPowerupActionSkillCost Management Update Component', () => {
    let comp: MPowerupActionSkillCostUpdateComponent;
    let fixture: ComponentFixture<MPowerupActionSkillCostUpdateComponent>;
    let service: MPowerupActionSkillCostService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MPowerupActionSkillCostUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MPowerupActionSkillCostUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MPowerupActionSkillCostUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MPowerupActionSkillCostService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MPowerupActionSkillCost(123);
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
        const entity = new MPowerupActionSkillCost();
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
