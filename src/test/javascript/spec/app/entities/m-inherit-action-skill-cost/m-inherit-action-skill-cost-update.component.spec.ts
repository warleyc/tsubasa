/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MInheritActionSkillCostUpdateComponent } from 'app/entities/m-inherit-action-skill-cost/m-inherit-action-skill-cost-update.component';
import { MInheritActionSkillCostService } from 'app/entities/m-inherit-action-skill-cost/m-inherit-action-skill-cost.service';
import { MInheritActionSkillCost } from 'app/shared/model/m-inherit-action-skill-cost.model';

describe('Component Tests', () => {
  describe('MInheritActionSkillCost Management Update Component', () => {
    let comp: MInheritActionSkillCostUpdateComponent;
    let fixture: ComponentFixture<MInheritActionSkillCostUpdateComponent>;
    let service: MInheritActionSkillCostService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MInheritActionSkillCostUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MInheritActionSkillCostUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MInheritActionSkillCostUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MInheritActionSkillCostService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MInheritActionSkillCost(123);
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
        const entity = new MInheritActionSkillCost();
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
