/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MMasterVersionDetailComponent } from 'app/entities/m-master-version/m-master-version-detail.component';
import { MMasterVersion } from 'app/shared/model/m-master-version.model';

describe('Component Tests', () => {
  describe('MMasterVersion Management Detail Component', () => {
    let comp: MMasterVersionDetailComponent;
    let fixture: ComponentFixture<MMasterVersionDetailComponent>;
    const route = ({ data: of({ mMasterVersion: new MMasterVersion(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMasterVersionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MMasterVersionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MMasterVersionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mMasterVersion).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
