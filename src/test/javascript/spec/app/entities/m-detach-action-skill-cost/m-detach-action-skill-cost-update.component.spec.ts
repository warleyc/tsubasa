/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MDetachActionSkillCostUpdateComponent } from 'app/entities/m-detach-action-skill-cost/m-detach-action-skill-cost-update.component';
import { MDetachActionSkillCostService } from 'app/entities/m-detach-action-skill-cost/m-detach-action-skill-cost.service';
import { MDetachActionSkillCost } from 'app/shared/model/m-detach-action-skill-cost.model';

describe('Component Tests', () => {
  describe('MDetachActionSkillCost Management Update Component', () => {
    let comp: MDetachActionSkillCostUpdateComponent;
    let fixture: ComponentFixture<MDetachActionSkillCostUpdateComponent>;
    let service: MDetachActionSkillCostService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MDetachActionSkillCostUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MDetachActionSkillCostUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MDetachActionSkillCostUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MDetachActionSkillCostService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MDetachActionSkillCost(123);
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
        const entity = new MDetachActionSkillCost();
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
