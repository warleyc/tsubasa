/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MEncountersCommandBranchUpdateComponent } from 'app/entities/m-encounters-command-branch/m-encounters-command-branch-update.component';
import { MEncountersCommandBranchService } from 'app/entities/m-encounters-command-branch/m-encounters-command-branch.service';
import { MEncountersCommandBranch } from 'app/shared/model/m-encounters-command-branch.model';

describe('Component Tests', () => {
  describe('MEncountersCommandBranch Management Update Component', () => {
    let comp: MEncountersCommandBranchUpdateComponent;
    let fixture: ComponentFixture<MEncountersCommandBranchUpdateComponent>;
    let service: MEncountersCommandBranchService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MEncountersCommandBranchUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MEncountersCommandBranchUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MEncountersCommandBranchUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MEncountersCommandBranchService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MEncountersCommandBranch(123);
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
        const entity = new MEncountersCommandBranch();
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
