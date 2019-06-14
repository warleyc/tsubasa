/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTargetNationalityGroupUpdateComponent } from 'app/entities/m-target-nationality-group/m-target-nationality-group-update.component';
import { MTargetNationalityGroupService } from 'app/entities/m-target-nationality-group/m-target-nationality-group.service';
import { MTargetNationalityGroup } from 'app/shared/model/m-target-nationality-group.model';

describe('Component Tests', () => {
  describe('MTargetNationalityGroup Management Update Component', () => {
    let comp: MTargetNationalityGroupUpdateComponent;
    let fixture: ComponentFixture<MTargetNationalityGroupUpdateComponent>;
    let service: MTargetNationalityGroupService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTargetNationalityGroupUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MTargetNationalityGroupUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MTargetNationalityGroupUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MTargetNationalityGroupService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MTargetNationalityGroup(123);
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
        const entity = new MTargetNationalityGroup();
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
