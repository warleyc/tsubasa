/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MMissionUpdateComponent } from 'app/entities/m-mission/m-mission-update.component';
import { MMissionService } from 'app/entities/m-mission/m-mission.service';
import { MMission } from 'app/shared/model/m-mission.model';

describe('Component Tests', () => {
  describe('MMission Management Update Component', () => {
    let comp: MMissionUpdateComponent;
    let fixture: ComponentFixture<MMissionUpdateComponent>;
    let service: MMissionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMissionUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MMissionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MMissionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MMissionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MMission(123);
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
        const entity = new MMission();
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
