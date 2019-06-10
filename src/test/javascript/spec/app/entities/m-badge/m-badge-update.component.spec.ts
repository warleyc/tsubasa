/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MBadgeUpdateComponent } from 'app/entities/m-badge/m-badge-update.component';
import { MBadgeService } from 'app/entities/m-badge/m-badge.service';
import { MBadge } from 'app/shared/model/m-badge.model';

describe('Component Tests', () => {
  describe('MBadge Management Update Component', () => {
    let comp: MBadgeUpdateComponent;
    let fixture: ComponentFixture<MBadgeUpdateComponent>;
    let service: MBadgeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MBadgeUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MBadgeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MBadgeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MBadgeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MBadge(123);
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
        const entity = new MBadge();
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
