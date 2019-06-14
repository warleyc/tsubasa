/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MActionSkillHolderCardContentUpdateComponent } from 'app/entities/m-action-skill-holder-card-content/m-action-skill-holder-card-content-update.component';
import { MActionSkillHolderCardContentService } from 'app/entities/m-action-skill-holder-card-content/m-action-skill-holder-card-content.service';
import { MActionSkillHolderCardContent } from 'app/shared/model/m-action-skill-holder-card-content.model';

describe('Component Tests', () => {
  describe('MActionSkillHolderCardContent Management Update Component', () => {
    let comp: MActionSkillHolderCardContentUpdateComponent;
    let fixture: ComponentFixture<MActionSkillHolderCardContentUpdateComponent>;
    let service: MActionSkillHolderCardContentService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MActionSkillHolderCardContentUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MActionSkillHolderCardContentUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MActionSkillHolderCardContentUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MActionSkillHolderCardContentService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MActionSkillHolderCardContent(123);
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
        const entity = new MActionSkillHolderCardContent();
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
