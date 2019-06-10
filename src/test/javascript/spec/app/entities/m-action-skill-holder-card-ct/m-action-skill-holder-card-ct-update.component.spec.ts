/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MActionSkillHolderCardCtUpdateComponent } from 'app/entities/m-action-skill-holder-card-ct/m-action-skill-holder-card-ct-update.component';
import { MActionSkillHolderCardCtService } from 'app/entities/m-action-skill-holder-card-ct/m-action-skill-holder-card-ct.service';
import { MActionSkillHolderCardCt } from 'app/shared/model/m-action-skill-holder-card-ct.model';

describe('Component Tests', () => {
  describe('MActionSkillHolderCardCt Management Update Component', () => {
    let comp: MActionSkillHolderCardCtUpdateComponent;
    let fixture: ComponentFixture<MActionSkillHolderCardCtUpdateComponent>;
    let service: MActionSkillHolderCardCtService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MActionSkillHolderCardCtUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MActionSkillHolderCardCtUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MActionSkillHolderCardCtUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MActionSkillHolderCardCtService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MActionSkillHolderCardCt(123);
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
        const entity = new MActionSkillHolderCardCt();
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
